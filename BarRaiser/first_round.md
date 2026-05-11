

Model a Player who can have many GameScores (one-to-many) and many Achievements (many-to-many). 
In a relational database, how would you design the tables and foreign key relationships to represent these entities and their connections?

Player -* GameScores
Player *-* Achievements

Game:


PlayerTable
PlayerId, Name, Gender

GameScoresTable
PlayerId, scoreNumber



AchievementsTable
AchievementsId, text



player-AchievementsTable
PlayerId, AchievementId
************************************************

Leaderboard:
GET
{Base_URL}/api/v1/dashboard/score/{dashboardId}

RequestParam
pageNo:
limit:



PATCH
{Base_URL}/api/v1/dashboard/{playerId}
Req body:
{
    Score: 5423
}


GET
{Base_URL}/api/v1/dashboard/{playerId}

************************************************


Sharding vs Partitioning 
NOSQL vs SQL
Consistent hashing 
Spring security 
CAP theorem with P meaning 

Why you use MongoDb, why not Cassandra?

How to debug application having memory timeout issue occuring at production?

In inMemory cache, which one you solve first: inconsistent data vs Deleted Data ?

************************************************


Implement a thread-safe SlidingWindowRateLimiter class. It should restrict requests 
for a given customerId based on a sliding time window.

Your implementation must have:

- A constructor: public SlidingWindowRateLimiter(int requestLimit, Duration timeWindow)

- A method: public boolean isAllowed(String customerId)

This method should return true if the number of requests from a specific customerId in the last
 timeWindow is less than the requestLimit. Each call to isAllowed counts as a request.



