#Apache JMeter performance testing results

the oneKCrud.jmx file represents the actual test used to gather the results.
The test can be viewed and results can be reproduced by using this file in conjunction with Apache JMeter.
<br><br>
The resultsNoMultitenancy folder contains test results where no multitenancy logic was used.
Here the (single) data source was defined in the application.yml file.
These results are meant to define a baseline.
<br><br>
the resultsMultitenancy folder contains the test results where the multitenancy logic was used.
Here there were two data sources defined in the application.yml file as described in the projects README.
<br><br>
Both test were run against the same simple CRUD API. Note that in both test cases there are calls for two tenants.
This is done so the load is the exact same for both tests.
The "no multitenancy" test simply ignored the tenantId header values and directed all calls to the same database.