import { Link, useNavigate } from 'react-router-dom';
import { useMediaQuery } from 'react-responsive';
import axios from 'axios';
import ShareResult from './ShareResult';
import home from '../assets/icons/mobile-home.png';
import back from '../assets/icons/mobile-back.png';
import restart from '../assets/icons/mobile-again.png';

const backBaseUrl = process.env.REACT_APP_BACKEND_URL;
const NumURL = `${backBaseUrl}/games`;

function ResultButtons({ isforOne, resultString, img, isFromGamePage, userNum, id }) {
  const navigate = useNavigate();
  const isMobile = useMediaQuery({
    query: '(max-width: 700px)',
  });

  // 랜덤페이지로 이동
  async function goToRandomPage(count) {
    await axios.post(NumURL, { player_num: count }).then(response => {
      navigate('../random', { replace: true, state: { playerNum: count, gameID: response.data } });
    });
  }

  function onClick() {
    // 이전 페이지가 게임페이지면 랜덤페이지로 이동(다시하기 버튼)
    if (isFromGamePage < 0) {
      // userNum 넘겨주기
      goToRandomPage(userNum);
    }
    // 뒤로가기 버튼
    else {
      navigate(`../resultmany?game-id=${isFromGamePage}`, { replace: false });
    }
  }

  if (isMobile) {
    return (
      <div className="inline-flex flex-row w-[90%] place-content-center gap-6 ">
        <button onClick={onClick} className="h-[6vh] w-[6vh] max-h-[10vw] max-w-[10vw]">
          <img className="h-[6vh] w-[6vh] max-h-[10vw] max-w-[10vw]" src={isFromGamePage < 0 ? restart : back} alt="" />
          {/* 다시하기 or 뒤로가기 */}
        </button>

        <Link to="/">
          <button className="h-[6vh] w-[6vh] max-h-[10vw] max-w-[10vw]">
            <img className="h-[6vh] w-[6vh] max-h-[10vw] max-w-[10vw]" src={home} alt="" />
            {/* 홈으로 */}
          </button>
        </Link>
        <ShareResult isforOne={isforOne} resultString={resultString} img={img} isMobile id={id} />
      </div>
    );
  }
  return (
    <div className="flex flex-row justify-center space-x-[5%]">
      <ShareResult isforOne={isforOne} resultString={resultString} img={img} isMobile={false} id={id} />

      <button
        onClick={onClick}
        className={`font-cookierun deskTop:text-[2.5vmin] deskTop:w-[30%] mobile:text-lg deskTop:max-w-[15vh]
          py-[0.3rem] rounded-full whitespace-nowrap
      ${isforOne ? 'bg-primary-3 text-primary-1 hover:bg-primary' : 'bg-black text-primary'}`}
      >
        {isFromGamePage < 0 ? '다시하기' : '뒤로가기'}
      </button>
      <Link to="/" className="deskTop:w-[30%]">
        <button
          className={`font-cookierun deskTop:text-[2.5vmin] mobile:text-lg deskTop:max-w-[15vh]
          py-[0.3rem] rounded-full whitespace-nowrap deskTop:w-[100%] 
      ${isforOne ? 'bg-primary-3 text-primary-1 hover:bg-primary' : 'bg-black text-primary'}`}
        >
          홈으로
        </button>
      </Link>
    </div>
  );
}

export default ResultButtons;
