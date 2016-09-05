function go(url)
{
	window.location = url;
}

function approveConstitution(url)
{
	var isOK = confirm("Are you sure to Approve?");
	if(isOK)
	{
		go(url);
	}
}