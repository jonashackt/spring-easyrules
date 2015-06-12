# spring-easyrules
[![Build Status](https://travis-ci.org/jonashackt/spring-easyrules.svg?branch=master)](https://travis-ci.org/jonashackt/spring-easyrules)
[![Coverage Status](https://coveralls.io/repos/jonashackt/spring-easyrules/badge.svg)](https://coveralls.io/r/jonashackt/spring-easyrules)

Small example project showing useage of [easyrules] and how to [define rules].

One Requirement of functional validation is often, that Rule-Data could be configured e.g. via property-files. This projects makes use of Spring Boot's @ConfigurationProperties Annotation. See the very good [blog-post from spring.io/blog] and the [Spring Boot docs].

Having the @EnableConfigurationProperties Annotation at your SpringBootApplication-Class in place, Spring automatically loads the rules.yml defined properties to your Rule-Pojos fields - without doing something yourself.

Just watch out - if you use cool YAML-Files for that - only use Camel-Case with one Letter big, not more (like myPropertything is ok, myPropertyThing is not!). Otherwise you get Errors you simply could´nt understand. At least i could´nt :) Also, in the Spring Boot Docs there is a reference to the yourhost:yourport/configprops - URL. There you could see your properties, if set up correctly. But this does not allways show the right contents, even if they are correctly loaded! So watch out a bit - and you´ll get fun with that stuff :D

For Eclipse development of YAML-Ruleproperty-Files, install the [Eclipse YAML Editor plugin YEdit]

[easyrules]:http://www.easyrules.org/index.html
[define rules]:http://www.easyrules.org/user-guide/defining-rules.html
[blog-post from spring.io/blog]:https://spring.io/blog/2015/01/13/configuring-it-all-out-or-12-factor-app-style-configuration-with-spring
[Spring Boot docs]:http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
[Eclipse YAML Editor plugin YEdit]:https://marketplace.eclipse.org/content/yedit