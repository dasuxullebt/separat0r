# separat0r

This program solves a problem I often ran into: You have a bunch of
files, which you – for example – want to send via e-mail, but the mail
provider limits the size of attachments. If your single files are too
large, then there is not much you can do about it, without using
multi-volume archives. If the single files are not, this program could
help: You pass it the maximum size of an attachment, and the files you
want to send. You will then receive a list of buckets which tell you
how you can send these files.

This problem is related to the [Knapsack problem][1], and probably has
no efficient solution. The algorithm used here is greedy, and appears
to give good results.

Send requests, patches and kappa maki to

    c    xu
     s  u  l  e
	  s@    .d

[1]:https://en.wikipedia.org/wiki/Knapsack_problem
