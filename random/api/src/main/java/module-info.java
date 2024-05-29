module random.api {
  exports org.example.random.api to app;
  requires random.core;

  requires spring.web;
}