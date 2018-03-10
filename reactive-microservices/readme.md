<h1>Sample Spring Reactive Microservices</h1>

<p>This Project using Spring Boot 2.0.0.RELEASE with Web Reactive and Mongodb Reactive. 
There are three module:<p/>

<ul>
    <li><b>Common:</b> Share models and libraries </li>
    <li><b>Account service:</b> Manges Account information</li>
    <li><b>Customer service:</b> Manges Customer information</li>
</ul>
<p>To get model share from Common, the Customer service and Account service will 
inject in pom.xml by include dependence to Common.</p>
<p> The Customer model has list Account. This service will call to Account Service 
to get list Account of Customter given by customerId.</p>

<h3>Running this project</h3>

<ol>
    <li>Start Account service: with port <i>127.0.0.1:8081</i> </li>
    <li>Start Customer service: with port <i>127.0.0.1:8082</i></li>
</ol>

<h3>Api Account service:</h3>
<p>host: 127.0.0.1:8081</p>
<ol>
    <li>Get Account given by customerId: <code style="color:blue">/account/customer/{customerId}</code> method GET</li>
    <li>Get All Account: <code style="color:blue">/account</code> method GET</li>
    <li>Get Account given by id: <code style="color:blue">/account/{id}</code> method GET</li>
    <li>Create Account given by Account object: <code style="color:blue">/account</code> method POST</li>
</ol>

<h3>Api Customer service:</h3>
<p>host: 127.0.0.1:8082</p>
<ol>
    <li>Get Customer given by id: <code style="color:blue">/customer/{id}</code> method GET</li>
    <li>Get Customer given by pesel: <code style="color:blue">/customer/accounts/{pesel}</code> method GET</li>
    <li>Create Customer given by Customer object: <code style="color:blue">/customer</code> method POST</li>
</ol>

<h3>Mongodb: mongo reactive</h3>
<p>Using docker to run docker container:</p>
<code>docker run -d --name mongo -p 27017:27017 mongo</code>
