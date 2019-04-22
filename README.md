# ReactiveList
This is a sample app with Kotlin to show how to use a recyclerview and Rx to load a large list from github without getting all the data at once and paginate them

The code originally is not mine, i just rewrote everything from scartch and slightly changes since i was playing around of it from a tutorial artice on Medium at this link:
https://medium.com/@Ahmed.AbdElmeged/android-paging-library-with-rxjava-and-rest-api-e5c229fd70ba
For more information and details take a look at the provided link above

This is the story which i used above tutorial to write it:

the functionality to get a list of "tetris"-repositories from the REST API call:
https://api.github.com/search/repositories?q=[Keyword]
The call returns JSON entries for each repository.

Further documentation can be found at:
https://developer.github.com/v3/search/#search-repositories

Parse the result and display the returned items in a list control of your choice.
Each item should show the name of the repository, the login-name of its owner and
the size of the repository.
Use a different background color for items that have "has_wiki" set to 'true'
Additional challenge:

Implement pagination: Get 10 entries with every REST-call and extend the list by 10
more whenever the list is scrolled to the end. Read the online documentation to find
out how (pay heed to the rate limit: https://developer.github.com/v3/search/#rate-
limit)

Add a free text field where a user can enter an arbitrary search string instead of
"[Keyword]"
