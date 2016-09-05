function go(url)
{
	window.location = url;
}

function newConstitution()
{
	window.location = "saveConstitution.do";
}


function deleteConstitution(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}
