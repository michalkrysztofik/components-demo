module imports {
  exports org.example.imports to api, importsLib;

  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
}