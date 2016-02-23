import twitter4j.*
import twitter4j.conf.*
import groovy.json.JsonSlurper

class TwitterWrapper {
	private Twitter twitter = null

	// Proxy On/Off: true - ON, false - OFF (default)
	private Boolean proxyOnOff = false
	
	def jsonSlurper = null
	
	TwitterWrapper(){}
	
	TwitterWrapper(String propFile) {
		twitter =  TwitterFactory.getSingleton()

		twitter.addRateLimitStatusListener (new RateLimitStatusListener() {
			void onRateLimitStatus(RateLimitStatusEvent e) {
				RateLimitStatus status = e.getRateLimitStatus()
				// println "RateLimitStatus: " + status
				if (status.remaining < 5) {
					def sleepTime = status.getSecondsUntilReset() + 15
					print "API rate limit reached. Sleeping for " + sleepTime + " sec(s) ..."
					sleep(sleepTime * 1000)
					println " done"
				}
			}

			void onRateLimitReached(RateLimitStatusEvent e) {}
		})
		
		// Instantiate JSON Slurper in case we need to parse HTTP responses
		jsonSlurper = new JsonSlurper()
	}

	def verifyCredentials() {
		twitter.verifyCredentials()
	}

	def getFriendsIDs(cur) {
		twitter.getFriendsIDs(cur)
	}

	def getFollowersIDs(id) {
		def nextCursor = -1
		def followers = []
		
		def page = []
		while (page.nextCursor != 0) {
			page = twitter.getFollowersIDs(id, nextCursor)
			nextCursor = page.nextCursor
			followers.addAll(page.ids)
		}
		
		return followers
	}

	def createFriendship(id) {
		try {
			twitter.createFriendship(id)
		}
		catch (TwitterException ex) {
			if (ex.statusCode == 403) {
				def error = jsonSlurper.parseText(ex.response.responseAsString).errors.getAt(0)
				println "${error.code}: ${error.message}"
			}
		}
	}

	def destroyFriendship(id) {
		twitter.destroyFriendship(id)
	}

	def lookupUser (id) {
		def user = null
		
		try {
			user = twitter.lookupUsers(id).first()
		}
		catch (TwitterException ex) {
			println ex
			if (ex.statusCode == 404) {
			}
		}

		return user
	}

	def lookupFriendship(id) {
		twitter.lookupFriendships(id).getAt(0)
	}
	
	def isFollowing(id) {
		def friendship = lookupFriendship(id)
		friendship.following
	}
	
	def isFollowedBy(id) {
		def friendship = lookupFriendship(id)
		friendship.followedBy
	}
	
}

