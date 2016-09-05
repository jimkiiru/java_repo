function go(url)
{
	window.location = url;
}

function newClub()
{
	window.location = "saveClub.do";
}

function deleteClub(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}