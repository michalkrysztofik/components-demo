module imports.api {
  opens org.example.imports.api to app;
  requires imports.core;

  requires spring.web;
}