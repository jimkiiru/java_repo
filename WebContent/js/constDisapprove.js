function go(url)
{
	window.location = url;
}

function disApproveConst(url)
{
	var isOK = confirm("Are you sure to DisApprove?");
	if(isOK)
	{
		go(url);
	}
}