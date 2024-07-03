module random.api {
  opens org.example.random.api to app;
  requires random.core;

  requires spring.web;
}