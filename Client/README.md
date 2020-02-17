# org.openapitools.client - Kotlin client library for Tracker

## Requires

* Kotlin 1.3.41
* Gradle 4.9

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*InformationApi* | [**rootGet**](docs/InformationApi.md#rootget) | **GET** / | Basic information about the API
*LocationApi* | [**locationLocIdGet**](docs/LocationApi.md#locationlocidget) | **GET** /location/{locId} | get location by id
*LocationApi* | [**locationPost**](docs/LocationApi.md#locationpost) | **POST** /location | Initialize location
*LocationApi* | [**locationPut**](docs/LocationApi.md#locationput) | **PUT** /location | Updates location
*UserApi* | [**loginPost**](docs/UserApi.md#loginpost) | **POST** /login | Login and get token


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.InlineObject](docs/InlineObject.md)
 - [org.openapitools.client.models.InlineObject1](docs/InlineObject1.md)
 - [org.openapitools.client.models.InlineObject2](docs/InlineObject2.md)
 - [org.openapitools.client.models.InlineResponse200](docs/InlineResponse200.md)
 - [org.openapitools.client.models.InlineResponse2001](docs/InlineResponse2001.md)
 - [org.openapitools.client.models.Location](docs/Location.md)
 - [org.openapitools.client.models.LocationLoc](docs/LocationLoc.md)
 - [org.openapitools.client.models.User](docs/User.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
