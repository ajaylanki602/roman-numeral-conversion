# roman-numeral-conversion
Adobe AEM take home assessment

## 1. Goals

Implementing HTTP Endpoint which must accept a URI in this format: http://localhost:8080/romannumeral?query={integer}
{integer} must be any integer value in the range 1-3999 as mentioned in Extension 1 . Errors can be returned in plain text format, but successful response must be a JSON document
equivalent to:
{
“input” : “1”,
“output” : “I”
}

Extension 1: To expand the range from 1-255 to 1-3999

Extension 3: To include additional DevOps capabilities in the project to represent preparing the project for ease of operation in a production environment (Like metrics, monitoring, logging). To include tooling to build a runnable Docker container for the service.



## 2. Tools Required for the project.

  JDK 8+, Maven 3.8
  

### 3. Running the program
  
#### a. Method 1
Clone the project using git clone command
  
     git clone https://github.com/ajaylanki602/roman-numeral-conversion.git
Navigate to the project folder 
  
    cd roman-numeral-conversion
    
To build the project, run the following command
    
    mvn clean install

Once the command is successfully run and the target folder is created with the roman-numeral-conversion-0.0.1-SNAPSHOT.jar
    cd target

run the jar file using java -jar and the name of the snapshot.Here it is 
"roman-numeral-conversion-0.0.1-SNAPSHOT.jar"

     java -jar roman-numeral-conversion-0.0.1-SNAPSHOT.jar


After sucessful start of the server, Use any browser or any tool to access the API. 
  
      http://localhost:8080/romannumeral?query={integer} , 
replace '{integer}' with any integer number between 1 and 3999 (included)`

*Note : If there is any application already using port 8080 you may get error saying the port 8080 is already in use. To resolve this, you can either change the port or stop the other process running on port 8080 if needed*

#### b. Method 2
	
   you can use docker to run the project. To install docker and its dependencies,click [here](https://docs.docker.com/get-docker/)
   
   pull the docker image from my docker hub using CLI using the uri
   
     docker pull roman-numeral-conversion-0.0.1-SNAPSHOT
   
   map port 8080 from host to 8080 in the container
   
     docker run -p 8080:8080 ajaykumarl/roman-numeral-conversion
 
 This will start the container and expose the api on the above ports
 
 Hit the API with either a "query" parameter
 
 For more on api-documentation go to the following url
    
     http://localhost:8080/swagger-ui.html

## 4. Development methodology

### Step 1: Requirement gathering 
	
 I'm able to infer below requirements based on the document provided 
	 
 1. Given an Integer input within the range 1-3999, HTTP response (JSON format) with input (Integer) and output (Roman value) attributes should be returned.
 2. Implementing DevOps capabilities which includes building docker image, monitoring capabilities, accessing metrics etc.

### Step 2: Design/Technology considerations  

   Java 8 and Springboot are used in the development of this application as springboot (in addition to providing embedded server which eliminates deployment of war) provides many production-ready features such as
   1. actuator which provides endpoints to monitor and interact with the application, 
   2. Auto-configuration for an application metrics facade that supports numerous monitoring systems such as Datadog, Dynatrace, New Relic etc., 
   3. ability to view and configure the log levels of application at run time.
   
### Step 3: Test cases

 Implemented test cases at both controller and service level. All the edge cases like, input number 0, negative numbers and strings containing alphabets are tested using junit



### 5. Packaging layout

 In order to access packaging layout, please access index.html located at roman-numeral-conversion/doc/index.html


## 6. Error/Exception handling

### Query value is blank/empty

The server will return a 400 ( BAD_REQUEST ) status code along with a json object:

	{
	  "timestamp": "2022-01-18T00:05:54.8311507",
	  "status": "BAD_REQUEST",
	  "message": "Query param cannot be empty"
	}


### Query value is 0
  
The server will return a 400 ( BAD_REQUEST ) status code along with a json object:

    {
	  "timestamp": "2022-01-18T00:01:51.1445906",
      "status": "BAD_REQUEST",
      "message": "Query parameter value is 0,Roman numerals start to count from one and had no symbol to represent 0."
    }


### Query value is not in range

The server will return a 400 ( BAD_REQUEST ) status code along with a json object:

    {
	  "timestamp": "2022-01-18T00:01:51.1445906",
	  "status": "BAD_REQUEST",
      "message": "The number entered must be between 1 and 3999"
    }
 
 
### Query value is not an integer

The server will return a 400 ( BAD_REQUEST ) status code along with a json object:

    {
	  "timestamp": "2022-01-18T00:01:51.1445906",
	  "status": "BAD_REQUEST",
	  "message": "Number format error occured. Input entered is not a valid number"
    }
	
	
## 7 Logging

SLF4J logger is used to generate log statements. Logging level and logfile name are configured in application.properties. A log file with the name specified is created in the root of the project.


## 8 Metrics

I used spring-boot-starter-actuator dependency to expose endpoints to access metrics such as health, info etc.
	
Links to all the monitoring and metrics are returned upon accessing http://localhost:8080/actuator endpoint 
	
	{
    "_links": {
        "self": {
            "href": "http://localhost:8081/actuator",
            "templated": false
        },
        "health": {
            "href": "http://localhost:8081/actuator/health",
            "templated": false
        },
        "health-path": {
            "href": "http://localhost:8081/actuator/health/{*path}",
            "templated": true
        },
        "info": {
            "href": "http://localhost:8081/actuator/info",
            "templated": false
        },
        "metrics-requiredMetricName": {
            "href": "http://localhost:8081/actuator/metrics/{requiredMetricName}",
            "templated": true
        },
        "metrics": {
            "href": "http://localhost:8081/actuator/metrics",
            "templated": false
        } 
			}
    }
	
Health check response received by accessing /actuator/health looks as below
	
	{
		"status": "UP",
		"components": {
			"diskSpace": {
				"status": "UP",
				"details": {
					"total": 254865829888,
					"free": 162417471488,
					"threshold": 10485760,
					"exists": true
				}
			},
			"ping": {
				"status": "UP"
			}
		}
	}

/actuator/info provides info about the project

	{
		"app": {
			"name": "Number-to-roman-conversion",
			"description": "Number to roman conversion app",
			"version": "0.0.1-SNAPSHOT"
		}
	}	


## 9. Containerization

  I used docker for containerization. This would be useful during CI/CD process where we can easily push the docker image to repository. I've generated the image and pushed it to docker hub.

Commands to create a docker image inside the project folder :
	
	mvn clean package && docker image build -t ajaykumarl/roman-numeral-conversion
  
	docker push ajaykumarl/roman-numeral-conversion


## 10. References

https://start.spring.io/ to create spring boot project with required dependencies
https://editor.swagger.io/ to generate swagger.yaml file.
https://www.calculatorsoup.com/calculators/conversions/roman-numeral-converter.php - for integer to roman validations



