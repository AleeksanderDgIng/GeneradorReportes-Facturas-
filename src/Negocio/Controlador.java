package Negocio;

import Reporte.ReporteFactura;
import Datos.SqlConsultas;


/**
 *
 * @author facastaneda
 * Clase encargada de la interacción entre las vistas y la base de datos
 */
public class Controlador {
    
    private SqlConsultas mySql = new SqlConsultas();
    
    //variables privadas
    private String ID_Cliente="";
    private String NumFactura="";
    private ReporteFactura Factura;

    
    //constructor
    public Controlador(){    
    }

    public boolean existe_cliente(String ID){
        this.ID_Cliente=ID;
        return mySql.existe("ID", " from clientes where id='"+ID+"';");
    }

    public String getNameCliente(){        
        return mySql.GetDataString("nombre", "select nombre from clientes where id='"+this.ID_Cliente+"';");
    }
    

    public Object[] getID_Producto(){
        return mySql.GetColumna("producto", "id", "select id from producto;");
    }

    public Object[][] getDataProducto(String idProducto){
        String[] columnas={"nombre","precioU","Cantidad"};
        Object[][] data = mySql.GetTabla(columnas, "producto", "select * from producto where id='"+idProducto+"';");
        return data;
    }

    //devuelve el numero de la factura de 10 caracteres alfabeticos aleatorios
    public String GenerarNumFactura(){
        String cod="FACT-";
        for(int i=1; i<=5;i++){
           int num = (int)((Math.random()*(90-65))+65);
           cod = cod  + (char) num;
        }
        NumFactura = cod;
        return cod;
    }
   

    public String getNumFactura(){
        return this.NumFactura;
    }

    public void CrearFactura(String NumeroFactura, String Vendedor){
        String[] datos = {NumeroFactura, Vendedor, "",ID_Cliente};
        mySql.Ejecutar_Insruccion_Sql(datos, "insert into factura(Num,Vendedor,observacion,id_cliente) values(?,?,?,?);");
    }

   

    //registra un producto en la tabla detalle, pero tambien actualiza la cantidad de la tabla producto
    public boolean RegistrarProducto(String idProducto,String NumFactura,String Cantidad, String precio, String total){
        String[] datos = {idProducto, NumFactura, Cantidad, precio, total};
        
        String[] datosP = {Cantidad,idProducto};
        if(
        mySql.Ejecutar_Insruccion_Sql(datosP, "update producto set cantidad=cantidad-? where ID=?;"))
        {
            return mySql.Ejecutar_Insruccion_Sql(datos, "insert into detalle(id_producto,num_factura,cantidad,precioU,total) values(?,?,?,?,?);");
        }else{
            return false;
        }
    }

     public Object[][] getDetalle(String nFactura){
        String[] columnas={"id_producto","cantidad","precioU","total"};
        Object[][] data = mySql.GetTabla(columnas, "detalle where num_factura='"+nFactura+"';", "select * from detalle where num_factura='"+nFactura+"';");
        return data;
    }

    public Object[][] getClientes(){
        String[] columnas={"ID","nombre","direccion","persona_telefono"};
        Object[][] data = mySql.GetTabla(columnas, "clientes", "select * from clientes;");
        return data;
    }

    public Double getTotal(){
        return mySql.GetDataDouble("t", "select round( sum( total ) , 2 ) as t from detalle where num_factura='"+this.NumFactura+"';");
    }
    
    // metodo para visualisar la factura con el jasperviwer
    public void VerFactura(){
        //String Total = getTotal()+"";
        Factura = new ReporteFactura();
        Factura.ver_Factura(NumFactura);
    }
    
    // metodo para exportar el PDF
    public void ExpPdf(){
        Factura = new ReporteFactura();
        Factura.exp_pdf(NumFactura);
    }
    
    
}
