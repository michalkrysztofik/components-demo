module imports.core {
  exports org.example.imports.core.domain to imports.api;

  requires java.net.http;
  requires spring.context;
}