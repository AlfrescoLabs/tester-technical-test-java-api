# Setup
The automated test project requires a running instance of Alfresco Content Repository (ACS) and Search Services at the least.
The test environment may be setup already. If not, the instructions are provided in the <Bring the Test Environment up> section below.

# Prerequisites
Java 11

Maven 3.2.0

Docker

Alfresco Content Services 5.2.2 or above

Search Services 1.2.0 or above

# Bring the Test Environment up

1. ACS + Search Services Healthcheck: Please ensure that Alfresco Content Repository (ACS) admin console > Search Services uses the right port and shows tracking status.
This could be setup using the packaging > docker > docker-compose.yml
In a command window, cd to the packaging > docker directory and Run docker-compose up
This should start an instance of ACS Repository with Share and Search Services.
    
2. Search services (Solr) Healthcheck can be performed using solr admin console at:

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

`mvn clean install -Dtest=FixMeTest`

# Search API Reference:
https://api-explorer.alfresco.com/api-explorer/#!/search/search

#AFTS Search Documentation:
Conjunction (AND Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-conjunct.html
Disjunction (OR Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-disjunct.html
Negation (NOT Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-negate.html

# Automating tests
Sample test classes: SetupTest and FixMeTest have been provided.
This should provide an example of data preperation methods and how search api can be tested.
FixMeTest includes some tests for testing Range search, as documented here: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-ranges.html

There are 2 challenges:
1. Run the FixMeTest test and analyse, fix any failures that you might come across.
2. Using the provided AFTS references above, design (note down, pseudocode) a few tests to test conjunction, disjunction and negation opertors in a search query. Implement some of these in SampleTest class. Data preparation example is provided and can be used or amended as per your needs.


