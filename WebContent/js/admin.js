function go(url)
{
	window.location = url;
}

function newAdmin()
{
	window.location = "saveAdmin.do";
}

function deleteAdmin(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}