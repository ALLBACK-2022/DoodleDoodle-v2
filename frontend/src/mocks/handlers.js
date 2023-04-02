import { rest } from 'msw';

export const handlers = [
  // 게임 시작 버튼 API
  rest.post('http://localhost:8080/api/v1/games', (req, res, ctx) => {
    return res(ctx.status(201), ctx.body({ data: { id: 1 } }));
  }),
];
