module app {
  opens org.example.app to spring.core, spring.beans, spring.context;

  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.core;
  requires spring.beans;
}