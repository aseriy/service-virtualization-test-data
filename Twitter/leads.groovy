@Grab(group='org.twitter4j', module='twitter4j-core', version='4.0.4')
@Grab(group='org.slf4j', module='slf4j-simple', version='1.2')

import org.slf4j.*
import groovy.util.logging.Slf4j
import twitter4j.*
import TwitterWrapper

final Logger logger = LoggerFactory.getLogger(leads.class)

def twitter = new TwitterWrapper("twitter4j.properties")
println twitter.verifyCredentials()
twitter.proxyOn()
println twitter.verifyCredentials()
System.exit(0)


// @handle
def leadHandle = this.args.getAt(0)

def me = twitter.verifyCredentials()

def lead = twitter.lookupUser(leadHandle)
logger.info "Followers of " + [lead.id, lead.screenName, lead.name].join(" | ")

def nextCursor = -1
def idsToFollow = []
while (idsToFollow.nextCursor != 0) {
	idsToFollow = twitter.getFollowersIDs(lead.id, nextCursor)
	// println idsToFollow.ids
	nextCursor = idsToFollow.nextCursor

	for (id in idsToFollow.ids) {
		if (id != me.id) {
			def user = twitter.lookupUser(id)
			println ([user.id, user.screenName, user.name, 'null', 'null', 'false'].collect {
				(it instanceof Long) ? it : '"' + it + '"'
			}.join(","))
		}
	}

}


