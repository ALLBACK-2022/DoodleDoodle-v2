import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Minus from '../assets/icons/minus.png';
import Plus from '../assets/icons/plus.png';

import { createGame } from '../api/Game';

// 인원수 설정 후 Main페이지에서 랜덤단어생성 페이지로 이동

function GameStartButton() {
  const [count, setCount] = useState(1);

  const navigate = useNavigate();

  const minusClicked = () => {
    if (count > 1) setCount(count - 1);
  };
  const plusClicked = () => {
    if (count < 6) setCount(count + 1);
  };

  async function start() {
    await createGame(count).then(response => {
      console.log(response);
      navigate('random', { replace: false, state: { playerNum: count, gameID: response.data.id } });
    });
  }

  return (
    <div className="flex mobile:flex-col mt-[10vh] items-center">
      <div className="deskTop:w-[50%] text-center">
        <button
          type="button"
          className="deskTop:text-[4.5vw] mobile:text-[10vw] text-primary-1 font-cookierun startshadow  textborder"
          onClick={start}
        >
          start
        </button>
      </div>

      <div className="flex deskTop:space-x-[2vw] mobile:space-x-[5vw] deskTop:w-[50%] justify-center">
        <button className="rounded-full" onClick={minusClicked}>
          <img src={Minus} alt="" className="deskTop:w-[4.5vw] mobile:w-[9vw]" />
        </button>
        <div className="deskTop:text-[4.5vw] mobile:text-[9vw] text-primary-1 font-cookierun startshadow textborder">
          <p>{count}</p>
        </div>
        <button className="rounded-full" onClick={plusClicked}>
          <img src={Plus} alt="" className="deskTop:w-[4.5vw] mobile:w-[9vw]" />
        </button>
      </div>
    </div>
  );
}

export default GameStartButton;
