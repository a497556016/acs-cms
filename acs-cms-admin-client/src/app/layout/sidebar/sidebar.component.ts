import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { SettingsService } from '@delon/theme';
import { UserLoginService } from '@core/services/user.login.service';

@Component({
  selector   : 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent {
    constructor(public settings: SettingsService, public msgSrv: NzMessageService,
      private userLoginService: UserLoginService
    ) {
    }

    logout(){
      this.userLoginService.logout();
    }
}
