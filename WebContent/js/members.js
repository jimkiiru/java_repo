function go(url)
{
	window.location = url;
}

function newMember()
{
	window.location = "saveMember.do";
}

function deleteMember(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}