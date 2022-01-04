let lastShownAnswerNo = -1;

const showAnswerInput = () => {
    let answer = document.getElementById(`answerSpan${lastShownAnswerNo + 1}`);
    if (answer === null) return;
    answer.hidden = false;
    lastShownAnswerNo++;
}

const hideClearAnswerInput = () => {
    let answer = document.getElementById(`answerSpan${lastShownAnswerNo}`);
    let input = document.getElementById(`answerList${lastShownAnswerNo}.answer`);
    if (answer === null) return;
    answer.hidden = true;
    input.value = '';
    lastShownAnswerNo--;
}