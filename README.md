# ScalaPlay2EssentialFilter

[![Build Status](https://travis-ci.org/vinstonpandiyan/ScalaPlay2EssentialFilter.svg?branch=master)](https://travis-ci.org/vinstonpandiyan/ScalaPlay2EssentialFilter.svg?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/78f6db9d16024552a7e9f3e9b781aec7)](https://www.codacy.com/app/vinstonpandiyan/scala-play2-activiti-integration?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vinstonpandiyan/scala-play2-activiti-integration&amp;utm_campaign=Badge_Grade) [![DockerHub](https://img.shields.io/badge/docker-available-blue.svg)](https://hub.docker.com/u/vinston/)

  The purpose of this example application is to explore elagant and easy way of **Play2 EssentialFilter** feature,
  will be used for Logging all the Http request and response objects in the application.

Play provides more powerful and a lower level filter API called **EssentialFilter** which gives you full access to the body of the request and can be add or remove any parameters in the body content/headers. 

This API allows you to wrap **EssentialAction** with another action, and when we invoke next, we get back an Accumulator. You could compose this with an Akka Streams Flow using the through method.

@see: https://github.com/vinstonpandiyan/ScalaPlay2EssentialFilter/tree/master/app/com/vin/filter/CustomFilter.scala


