const item_ip = document.querySelector('.item_ip');
const btn_g = document.querySelector('.btn_g');
const button_round = document.querySelector('.button_round');
const submit_flag = document.querySelector('#submit_flag');
var flag = 0;

item_ip.onkeypress = () => {
	if(window.event.keyCode == 13){  // keycode 13 = enter
		window.event.preventDefault(); // 기본으로 내장되어 있는 이벤트를 죽인다.
		submit_flag.value = 1; //  마침 버튼
		onSubmit();
	}
}

btn_g.onclick = () => {
	submit_flag.value = 1;//  마침 버튼
	onSubmit();
}

button_round.onclick = () => {
	submit_flag.value = 2;//  인증 버튼
	onSubmit();
}

function onSubmit(){
	const msg1 = document.querySelector('.msg1');
	
	if (item_ip.value.length == 0) {
		msg1.style.display = 'block';
	} else {
		const form = document.querySelector('form'); 
		form.submit();
	}
}
