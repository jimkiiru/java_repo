function go(url)
{
	window.location = url;
}

function newEvent()
{
	window.location = "saveEvent.do";
}

function deleteEvent(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}