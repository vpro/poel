![Travis CI build status](https://travis-ci.org/vpro/poel-backend.svg?branch=master)

# poel-backend

The famous VPRO Poel!

## adding front end (vendor) resources

For now this means installing them through the `package.json` and NPM and creating
a vendor script in `package.json` that after running `npm run vendor` copies the
vendor script to `src/main/resources/static/vendor`.
