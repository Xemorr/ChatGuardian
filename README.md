# Chat Guardian - not just a simple text filter!

Like any automatic moderation plugin, it can be used to catch simple swear words
![demonstrating its ability to catch simple swear words](https://cdn.modrinth.com/data/cached_images/d41afb0b8a79a1f6a0f83d39c4013ef6047e5f02.png)
however, unlike any other plugin out there, Chat Guardian actually understands the **meaning** of what is being said! For example, if you banned the phrase 'nobody likes you', it might also ban 'everybody hates you'
![Demonstration of everybody hates you being a banned phrase](https://cdn.modrinth.com/data/cached_images/55ca6a26213d87a4b590bf3e769a4f435d3de909.png)

Unlike many other AI solutions, I'm not forcing you to agree with what messages I think are inappropriate. You have **full control** over which messages are considered appropriate to alert for on your server! 

Example Config: 
```
type: sentence2vec
blacklist:
  "die": 0.6
  "go fuck yourself": 0.55
  "fuck": 0.55
  "smelly": 0.55
  "faggot": 0.6
```, the higher number the closer a message has to be in meaning to the blacklisted message before it would be alerted for. Higher = Stricter!
