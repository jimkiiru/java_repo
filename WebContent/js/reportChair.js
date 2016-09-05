function go(url)
{
	window.location = url;
}

function newReport()
{
	window.location = "upload.do";
}

function deleteReport(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}