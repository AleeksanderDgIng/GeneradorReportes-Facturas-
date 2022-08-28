package Reporte;

import Datos.ConexionSQL;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author facastaneda
 */
public class ReporteFactura {

    //Estable una conexion con la base de datos
    private ConexionSQL con = new ConexionSQL();

    public ReporteFactura() {
    }

    public void ver_Factura(String NumFactura) {
        JasperReport reporte;
        JasperPrint reporte_view;
        try {

        // JasperCompileManager.compileReportToFile(
        //    "/Negocio/jcReportFactura.jrxml",
        //    "/Negocio/jcReportFactura.jasper");
        
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Reporte/jcRprueba.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);

            //Crea un objeto HashMap
            Map parametros = new HashMap();

            //limpia los parametros
            parametros.clear();

            //parametros de entrada
            parametros.put("NumFactura", NumFactura);

            reporte_view = JasperFillManager.fillReport(reporte, parametros, con.getConnection());
            JasperViewer.viewReport(reporte_view);

            //terminamos la conexion a la base de datos
            con.desconectar();
        } catch (JRException E) {
            E.printStackTrace();
        }
    }

    //Metodo para exportar el reporte a PDF
    public void exp_pdf(String NumFactura) {
        JasperReport reporte;
        JasperPrint reporte_view;
        try {
            //se carga el reporte
            URL in = this.getClass().getResource("/Reporte/jcRprueba.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //se procesa el archivo jasper
            Map parametros = new HashMap();
            parametros.clear();
            //parametros de entrada
            parametros.put("NumFactura", NumFactura);

            reporte_view = JasperFillManager.fillReport(reporte, parametros, con.getConnection());

            //se crea el archivo PDF
            JasperExportManager.exportReportToPdfFile(reporte_view, "/Users/facastaneda/NetBeansProjects/ReporteFactura/src/pdf/factura.pdf");
        } catch (JRException E) {
            E.printStackTrace();
        }
    }

}
