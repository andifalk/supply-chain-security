# AngularApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.2.1.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Generate CycloneDX SBOM

To export the SBOM in CycloneDX format from this project just use the `cyclonedx-npm` npm package.

First install it into this project:

```shell
npm install --save-dev @cyclonedx/cyclonedx-npm
```

Then, to create the SBOM, just run:

```shell
npx @cyclonedx/cyclonedx-npm --mc-type application --output-file angular-app-sbom.json
```

That's all, now import this into a suitable application like OWASP Dependency Track to track current and future vulnerabilities.
