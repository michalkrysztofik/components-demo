module importsLib {
  requires imports;
  provides org.example.imports.Importer with org.example.lib.DataImporter;

  requires java.net.http;
  requires spring.context;
}