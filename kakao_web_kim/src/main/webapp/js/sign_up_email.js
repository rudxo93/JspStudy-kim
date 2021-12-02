const flag = document.querySelector('#flag'); // flag값 가져오기

if (flag.value == 1) { // flag의 값이 1과 같은지??
	const msg2 = document.querySelector('.msg2'); // . = class / # = id
	msg2.style.display = 'block'; // flag가 1이면 존재하는 아이디 msg2 보여주기
}

const item_ip = document.querySelector('.item_ip');
const btn_g = document.querySelector('.btn_g');

item_ip.onkeypress = () => {
	if (window.event.keyCode == 13) {  // keycode 13 = enter
		window.event.preventDefault(); // 기본으로 내장되어 있는 이벤트를 죽인다.
		onSubmit();
	}
}

btn_g.onclick = () => {
	onSubmit();
}

function onSubmit() {
	if (item_ip.value.length == 0) {
		const msg1 = document.querySelector('.msg1');
		const msg2 = document.querySelector('.msg2');
		msg1.style.display = 'block';
		msg2.style.display = 'none';
	} else {
		const form = document.querySelector('form');
		form.submit();
	}
}