function go(url)
{
	window.location = url;
}

function newCategory()
{
	window.location = "saveCategory.do";
}

function deleteCategory(url)
{
	var isOK = confirm("Are you sure to delete?");
	if(isOK)
	{
		go(url);
	}
}