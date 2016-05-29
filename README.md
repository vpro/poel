![Travis CI build status](https://travis-ci.org/vpro/poel.svg?branch=master)

# VPRO Poel

The software for the famous VPRO Poel!

## Running

Make sure you have [Oracle JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.

Running `./mvnw spring-boot:run` should do the trick! If you have a local
installation of [Apache Maven](https://maven.apache.org/) 3.x, you can also use `mvn` instead of the
supplied `mvnw` script.

After starting up the Poel should be live at
[http://localhost:8080/](http://localhost:8080/).

## For developers

Some notes for the fine people developing this project.

### DevTools and LiveReload

It is recommended to run the project from an IDE, so you can use the [Spring
Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html)
for LiveReload and automatic restarts.

Install the [LiveReload extension](http://livereload.com/extensions/) for
your browser. When the server is running you should be able to enable
LiveReload in your browser.

DevTools monitors the classpath and when any changes are detected a LiveReload
or restart will be performed, as appropriate.

From the DevTools documentation:

> The way in which you cause the classpath to be updated depends on the IDE
> that you are using. In Eclipse, saving a modified file will cause the
> classpath to be updated and trigger a restart. In IntelliJ IDEA, building the
> project (Build → Make Project) will have the same effect.

Note: the default shortcut for Make Project in IntelliJ IDEA on OS X is ⌘F9.

Using LiveReload when running using the Spring Boot Maven plugin isn't the best
workflow right now. You can run the application using `mvn spring-boot:run` in
one terminal and for instance run `mvn process-resources` in another to update
the `target` directory, but this will currently trigger a restart of the
application (although the [documentation on automatic restarts](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html#using-boot-devtools-restart-exclude)
suggests this shouldn't happen).

### Front end developing

#### Vendor resources
For now this means installing them through the `package.json` and NPM and copying everything
you need to the `src/main/resources/static/vendor` folder.

#### JS
JavaScript is being optimised by default. There is a `watch:js` task to compile and lint
the production bundle. In `src/main/resources/templates/macros/footer.ftl` you can
switch to the development version of JS.

#### CSS
We're using [SASS](http://sass-lang.com/) to generate CSS. The CSS is automatically generated during the Maven build.

There is a separate `npm run watch:css` script to regenerate the CSS on any changes.
Be sure to follow the instructions of [DevTools and LiveReload](#DevTools and LiveReload)
to refresh the CSS for the browser through your IDE.

#### Watch it all
There is also an NPM `watch` task that watches bot JS and CSS.
