
window.onload = async()=>{
  let url_str = window.location.href;
  let url = new URL(url_str);
  let type = url.searchParams.get("type");
  let id = url.searchParams.get("id");
  if(id){
    let json = await fetch('editData',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          'type':'update',
          'id': id
        })
      }).then(res => res.json());
      document.getElementById("id").value=json.employee.id;
      document.getElementById("firstname").value=json.employee.firstname;
      document.getElementById("lastname").value=json.employee.lastname;
      document.getElementById("email").value=json.employee.email;
      document.getElementById("dob").value=json.employee.dob;
      document.getElementById("role").value=json.employee.role;

      document.getElementById("door").value=json.address.doorNo;
      document.getElementById("street").value=json.address.street;
      document.getElementById("city").value=json.address.city;
  }
  else{
    let id=await fetch('editData',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          'type':'insert'
        })
      }).then(res => res.json());
      document.getElementById("id").value=id;
  }
}
