import url from './setting/url';
import StaticTableState from './data/StaticTableState'
import IdentityState from './data/IdentityState'

let page = {};
page.pSizes = [5, 10, 30, 50, 100];
page.pLayout = "total, sizes, prev, pager, next, jumper";
page.pageSize = 10;
page.pageNum = 1;
page.total = 0;

let global = {
    "url": url.url,
    "updateUrl": url.updateUrl,
    "updateUrl2": url.updateUrl2,
    "imgFile": url.imgFile,
    "pSizes": page.pSizes,
    "pLayout": page.pLayout,
    "pageSize": page.pageSize,
    "pageNum": page.pageNum,
    "total": page.total,
    "StaticTableState":StaticTableState,
    IdentityState: IdentityState,
}

export default global;
