**CENTRAL CONFIG SERVER**
--------------------------------------

Centrals config server for micro services system. The configuration files is stored in git, every change will reload from end point. This sample will get _**message content**_ from config client in github through Config Server.

- Config Server: `/localhost:8888`, view file config of client-config: `/localhost:8888/config-client/default`
- Config Client: `/localhost:8080`, view message in MessageController `/localhost:8080/message` and reload config-client when changed `localhost:8080/refresh`
