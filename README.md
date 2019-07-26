# Setup
The automated test project requires a running instance of ACS Repo and Search Services at the least. This will be covered in the Test Environment Setup stage of this test. 

For more details about the related projects or their deployment see: [Search-Discovery] (https://git.alfresco.com/search_discovery) and (https://github.com/Alfresco/SearchServices).

# Prerequisites
Java 11

Maven 3.2.0

Docker

Alfresco Content Services 5.2.2 or above

Search Services 1.2.0 or above

# Bring the Test Environment up

1. ACS + Search Services Healthcheck: Please ensure that repo admin console > Search Services uses the right port and shows tracking status. Follow the points below to do so.

a) This could be setup using the packaging > docker > docker-compose.yml

b) In a command window, cd to the packaging > docker directory and Run docker-compose up

c) This should start an instance of ACS Repository with Share and Search Services.

d)

```
	<protocol>://<repo-host-ip>:<alfresco-port>/alfresco/#

	e.g. http://localhost:8080/alfresco/#
	
```
	
e) Go to Alfresco Administration Console (admin only)

	username: admin
	password: admin
	
f) Go to Search Service under Repository Services

g) Ensure correct port is allocated according to the docker compose and shows the tracking status

    
2. Solr Healthcheck can be performed using solr admin console at:

```
    <protocol>://<repo-host-ip>:<solr-port>/solr/#

    e.g. http://localhost:8083/solr/#
```

# Compile the project
`mvn clean install -DskipTests`

# Run the tests
`mvn clean install`

# Run a specific class of tests
`mvn clean install -Dtest=<class>`

e.g.

`mvn clean install -Dtest=CustomModelTest`

# Search API Reference:
https://api-explorer.alfresco.com/api-explorer/#!/search/search

# AFTS Search Documentation:
Conjunction (AND Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-conjunct.html

Disjunction (OR Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-disjunct.html

Negation (NOT Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-negate.html

# Automating tests
Sample test classes: SetupTest and SearchWithCustomModelTest have been provided.

This should provide an example of data preperation methods and how search api can be tested.

SearchWithCustomModelTest includes some tests for testing Range search, as documented here: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-ranges.html

There are 2 challenges:
1. Run the SearchWithCustomModelTest test and analyse, fix any failures that you might come across.

2. Using the provided AFTS references above, design (note down, psedocode) a few tests to test conjunction, disjunction and negation opertors in a search query. Implement some of these in SearchSampleTest class. Datapreparation example is provided and can be used or amended as per your needs.