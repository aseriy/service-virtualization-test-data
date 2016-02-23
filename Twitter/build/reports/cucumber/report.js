$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("twitter-api.feature");
formatter.feature({
  "line": 1,
  "name": "Twitter API",
  "description": "",
  "id": "twitter-api",
  "keyword": "Feature"
});
formatter.before({
  "duration": 145180,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Twitter instance",
  "keyword": "Given "
});
formatter.match({
  "location": "twitter-api_steps.groovy:4"
});
formatter.result({
  "duration": 166029120,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "API calls discovery",
  "description": "",
  "id": "twitter-api;api-calls-discovery",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 17,
      "name": "@discovery"
    }
  ]
});
formatter.step({
  "line": 19,
  "name": "user \"AlanSanie\"",
  "keyword": "Given "
});
formatter.step({
  "line": 20,
  "name": "request my credentials",
  "keyword": "When "
});
formatter.step({
  "line": 21,
  "name": "request the list of followers",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "examine my friendship with \"AlanSanie\"",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "follow and un-follow \"AlanSanie\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "AlanSanie",
      "offset": 6
    }
  ],
  "location": "twitter-api_steps.groovy:16"
});
formatter.result({
  "duration": 2308236092,
  "status": "passed"
});
formatter.match({
  "location": "twitter-api_steps.groovy:8"
});
formatter.result({
  "duration": 90072712,
  "status": "passed"
});
formatter.match({
  "location": "twitter-api_steps.groovy:21"
});
formatter.result({
  "duration": 129608784,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "AlanSanie",
      "offset": 28
    }
  ],
  "location": "twitter-api_steps.groovy:39"
});
formatter.result({
  "duration": 218056335,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "AlanSanie",
      "offset": 22
    }
  ],
  "location": "twitter-api_steps.groovy:66"
});
formatter.result({
  "duration": 521961730,
  "error_message": "401:Authentication credentials (https://dev.twitter.com/pages/auth) were missing or incorrect. Ensure that you have set valid consumer key/secret, access token/secret, and the system clock is in sync.\nmessage - Invalid or expired token.\ncode - 89\n\nRelevant discussions can be found on the Internet at:\n\thttp://www.google.co.jp/search?q\u003d18982322 or\n\thttp://www.google.co.jp/search?q\u003de53d7ce2\nTwitterException{exceptionCode\u003d[18982322-e53d7ce2], statusCode\u003d401, message\u003dInvalid or expired token., code\u003d89, retryAfter\u003d-1, rateLimitStatus\u003dnull, version\u003d4.0.4}\n\tat twitter4j.HttpClientImpl.handleRequest(HttpClientImpl.java:164)\n\tat twitter4j.HttpClientBase.request(HttpClientBase.java:57)\n\tat twitter4j.HttpClientBase.post(HttpClientBase.java:86)\n\tat twitter4j.TwitterImpl.post(TwitterImpl.java:1822)\n\tat twitter4j.TwitterImpl.destroyFriendship(TwitterImpl.java:447)\n\tat twitter4j.api.FriendsFollowersResources$destroyFriendship$2.call(Unknown Source)\n\tat twitter4j.api.FriendsFollowersResources$destroyFriendship$0.call(Unknown Source)\n\tat TwitterWrapper.destroyFriendship(TwitterWrapper.groovy:72)\n\tat TwitterWrapper$destroyFriendship$5.call(Unknown Source)\n\tat twitter-api_steps$_run_closure11.doCall(twitter-api_steps.groovy:81)\n\tat ✽.And follow and un-follow \"AlanSanie\"(twitter-api.feature:23)\n",
  "status": "failed"
});
formatter.after({
  "duration": 26525,
  "status": "passed"
});
});