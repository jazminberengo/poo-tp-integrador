package reportes;

public interface ElementoReportable {
	
	public void accept( ItemVisitor itemVisitor );
}
