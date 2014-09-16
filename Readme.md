# Wildcard

Wildcard is a simple library intended to replace regular expression invocations when limited functionality is required.  
The advantage of this is an order of magnitude faster than the standard regex engine.

For example, if you want to match all text that starts with the word 'pmbr' from a set of text like {'qwer', 'pmbrqwer', 'tyumpo'}, 
one may use a regular expression like 'pmbr.*'.  Instead, we can provide 'pmbr*', avoid the regex engine invocation,
and save CPU time.

How much of a difference is there?

Given a random set of alphanumeric text of 50 characters, inserting between 0 and 4 special characters ('?', '*'), over 100,000 invocations,
about an order of magnitude difference in required CPU time.

This comparison was performed on a Intel Core 2 Quad Q8400, windows 7, and JDK 1.7.0.67.