module imports.api {
  exports org.example.imports.api to app;
  requires imports.core;

  requires spring.web;
}