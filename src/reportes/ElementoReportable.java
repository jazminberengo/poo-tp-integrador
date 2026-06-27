package reportes;

public interface ElementoReportable {
	
	public void accept( ReporteVisitor visitor );
}
