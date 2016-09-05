function go(url)
{
	window.location = url;
}

function activateClub(url)
{
	var isOK = confirm("Are you sure to Activate?");
	if(isOK)
	{
		go(url);
	}
}