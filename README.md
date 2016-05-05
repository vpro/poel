![Travis CI build status](https://travis-ci.org/vpro/poel.svg?branch=master)

# VPRO Poel

The software for the famous VPRO Poel!

## Running

Make sure you have Oracle JDK 8 installed.

Running `./mvnw spring-boot:run` should do the trick! If you have a local installation of Apache Maven 3.x, you can also use `mvn` instead of the supplied `mvnw` script.

After starting up the poel should be live at [localhost:8080](http://localhost:8080/)

## Adding front end (vendor) resources

For now this means installing them through the `package.json` and NPM and creating
a vendor script in `package.json` that after running `npm run vendor` copies the
vendor script to `src/main/resources/static/vendor`.
