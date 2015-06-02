function pg_add_contacts(){
     
        $(".pg_add_contacts").bind("tap",function(){
             
            //获取对应的联系人的值
            //我这里直接从页面获取。也可以通过jsonp或者ajax获取都行
            var ctt_name = $(".name").html();
            var ctt_nikename = $(".nikename").html();
            var ctt_tel = $(".tel").html();
            var ctt_address = $(".address").html();
            var ctt_notes = $(".notes").html()
             
            //创建一个对象
            var contact = navigator.contacts.create();
             
            //姓名
            contact.displayName = ctt_name;
             
            //昵称
            contact.nickname = ctt_nikename;
             
            //备注
            contact.note = ctt_notes;
             
            //地址
            contact.addresses = [{
                "type":"home",
                "pref":false,
                "formatted":ctt_address //地址变量
            }]
             
            //电话
            var phoneNumbers = []; 
            phoneNumbers[0] = new ContactField('work', ctt_tel, false); 
            contact.phoneNumbers = phoneNumbers; 

            //保存
            contact.save(add_contact_succ,add_contact_err);
        })
    }
     
    document.addEventListener("deviceready", pg_add_contacts, false); 

//添加成功的函数  
function add_contact_succ(contact) {
    $.ui.popup( "添加成功" );
};
//添加失败的函数
function add_contact_err(contactError) {
    $.ui.popup( "添加失败" )
};