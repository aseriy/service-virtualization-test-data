import static cucumber.api.groovy.EN.*
import TwitterWrapper

Given(~/^Twitter instance$/) { ->
	twitter = new TwitterWrapper("twitter4j.properties")
}

When(~/^request my credentials$/) { ->
	iam = twitter.verifyCredentials()
}

Then(~/^confirm my screen name$/) { ->
	println "My screen name is ${iam.screenName}"
}

Given(~/^user "([^"]*)"$/) { String screenName ->
	user = twitter.lookupUser(screenName)
	twitter.lookupUser(user.id)
}

When(~/^request the list of followers$/) { ->
	followers = twitter.getFollowersIDs(user.id)
	twitter.getFollowersIDs(user.screenName)
}

Then(~/^confirm the number of followers$/) { ->
	println "User ${user.screenName} has ${followers.size()} followers"
}

When(~/^get user profile for each follower$/) { ->
	userProfiles = [:]
	followers.each {
		def follower = twitter.lookupUser(it)
		userProfiles.put(it, follower)
		twitter.lookupUser(follower.screenName)
	}
}

When(~/^examine my friendship with "([^"]*)"$/) { String screenName ->
		def user = twitter.lookupUser(screenName)
		twitter.lookupFriendship(user.screenName)
		twitter.lookupFriendship(user.id)
}

When(~/^examine my friendships with the followers$/) { ->
    friendships = [:]
	
	followers.each {
		if (it != iam.id) {
			def follower = userProfiles.containsKey(it) ? userProfiles.get(it) : twitter.lookupUser(it)
			friendships.put(follower.id, twitter.lookupFriendship(follower.id))
			twitter.lookupFriendship(follower.screenName)
		}
    }
}

Then(~/^confirm the friendships$/) { ->
    friendships.keySet().each {
		if (it != iam.id) {
			def friendship = friendships.get(it)
			println "${friendship.screenName}\tFollowing: ${friendship.following}\tFollowed by: ${friendship.followedBy}"
		}
	}
}

When(~/^follow and un-follow "([^"]*)"$/) { String screenName ->
	def user = twitter.lookupUser(screenName)
		
	if (user.protected) {
		println "User ${user.screenName} has protected twits."
	} else {
		if (twitter.isFollowing(user.id)) {
			twitter.destroyFriendship(user.id)
			twitter.createFriendship(user.id)
			twitter.destroyFriendship(user.screenName)
			twitter.createFriendship(user.screenName)
		} else {
			twitter.createFriendship(user.id)
			twitter.destroyFriendship(user.id)
			twitter.createFriendship(user.screenName)
			twitter.destroyFriendship(user.screenName)
		}
	}
}

When(~/^follow and un-follow each user except the protected ones$/) { ->
	protectedUsers = [:]

	// Flip by ID
	followers.each {
		def follower = userProfiles.containsKey(it) ? userProfiles.get(it) : twitter.lookupUser(it)
		
		if (follower.protected) {
			protectedUsers.put(follower.id, follower)
		} else  if (it != iam.id) {
			if (friendships.get(it).following) {
				println "Y ${follower.screenName} (${follower.id})"
				twitter.destroyFriendship(follower.id)
			} else {
				println "N ${follower.screenName} (${follower.id})"
				twitter.createFriendship(follower.id)
			}
		}
	}
	
	// Flop by ID
    followers.each {
		def follower = userProfiles.getAt(it)
		
		if (follower.protected) {
			protectedUsers.put(follower.id, follower)
		} else if (it != iam.id) {
			if (friendships.get(it).following) {
				println "Y ${follower.screenName} (${follower.id})"
				twitter.createFriendship(follower.id)
			} else {
				println "N ${follower.screenName} (${follower.id})"
				twitter.destroyFriendship(follower.id)
			}
		}
	}

	// Flip by screenName
	followers.each {
		def follower = userProfiles.getAt(it)
		
		if (follower.protected) {
			protectedUsers.put(follower.id, follower)
		} else  if (it != iam.id) {
			if (friendships.get(it).following) {
				println "Y ${follower.screenName} (${follower.id})"
				twitter.destroyFriendship(follower.screenName)
			} else {
				println "N ${follower.screenName} (${follower.id})"
				twitter.createFriendship(follower.screenName)
			}
		}
	}
	
	// Flop by screenName
    followers.each {
		def follower = userProfiles.getAt(it)
		
		if (follower.protected) {
			protectedUsers.put(follower.id, follower)
		} else if (it != iam.id) {
			if (friendships.get(it).following) {
				println "Y ${follower.screenName} (${follower.id})"
				twitter.createFriendship(follower.screenName)
			} else {
				println "N ${follower.screenName} (${follower.id})"
				twitter.destroyFriendship(follower.screenName)
			}
		}
	}
	
}

Then(~/^report the protected users$/) { ->
	println "${protectedUsers.size()} user(s) with protected twits:"
	protectedUsers.keySet().each {
		def user = protectedUsers.get(it)
		println "\t${user.screenName} (${user.id})"
	}
}

