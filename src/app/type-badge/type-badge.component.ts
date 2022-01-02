import { Component, OnInit } from '@angular/core';
import {TypeBadgeService} from '../service/type-badge/type-badge.service';
import {FileService} from '../service/type-badge/file.service';
import validate = WebAssembly.validate;
import {HttpClientModule} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-test',
  templateUrl: './type-badge.component.html',
  styleUrls: ['./type-badge.component.css']
})
export class TypeBadgeComponent implements OnInit {

  public typeBadge:any;
  public size:number=10;
  public currentPage:number=0;
  public totalPages:number;
  public pages:Array<number>;
  private currentKeyword: string="";
  public url="./assets/badge/service.png"
  public imageIpud :any="";
  private selectFile :File=null;
  filenames: string[] = [];
  fileStatus = { status: '', requestType: '', percent: 0 };
  public etat :number=1;
public typeBadgeE:any;
  private s:any="";
  constructor(private typeBadgeService:TypeBadgeService,private http:HttpClientModule,private toastr: ToastrService,protected router: Router, protected keycloakAngular: KeycloakService,private fileService: FileService) { }

  ngOnInit() {

    this.onGetTypeBadge();
  }

  onGetTypeBadge(){
    this.typeBadgeService.getTypeBadges(this.currentPage,this.size)
      .subscribe(data=>{

        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.typeBadge=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })
  }


  onPageProduct(i: number) {
    this.currentPage=i;
    //this.onGetTypeBadge();
    this.chercherTitre();
  }
  onCherche(value: any){
    this.currentPage=0;
    this.currentKeyword=value.keyword;
    this.chercherTitre();
  }
  chercherTitre() {

    this.typeBadgeService.getTypeBadgesCherche(this.currentPage,this.size,this.currentKeyword)
      .subscribe(data=>{
        this.totalPages=data["page"].totalPages;
        this.pages=new Array<number>(this.totalPages);
        this.typeBadge=data;
        //console.log(this.totalPages);
      },err => {
        console.log(err)
      })

  }

  onDeleteProduct(p: any) {
    let conf=confirm("Etes vous sûre?");
    if(conf){
      this.showNotification('top', 'center',4);
      this.typeBadgeService.deleteTypeBadges(p._links.self.href)
        .subscribe(data=>{
          this.chercherTitre();
        },err => {
          console.log(err);
        }

      );
    }
  }


  onSaveProduct(value: any) {
   // console.log(value);
   /**/
    console.log('ici charge')
    const formData = new FormData();
      //rest api add type badge start
      console.log("Name picture "+this.imageIpud[0].name)
      console.log(value);
      value.chart=this.imageIpud[0].name;
      console.log(value);
      this.typeBadgeService.saveTypeBadges(this.typeBadgeService.host+"/TYPE-BADGE-SERVICE/typeBadges",value)
        .subscribe(resp=>{
          this.showNotification('top', 'center',2);
          // image upload start
          for (const file of this.imageIpud) { formData.append('files', file, file.name); }
          this.fileService.upload(formData).subscribe(
            event => {
              console.log(event);
              console.log(this.fileStatus.percent);
              this.resportProgress(event);
            },
            (error: HttpErrorResponse) => {
              console.log(error);
            }
          );
          // image upload end
        },err => {
          console.log(err);
        })
      //end




  }

  onselectFile(e) {
    if(e.target.files){
      var reader=new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      this.selectFile=<File>e.target.files[0];

      reader.onload=(event:any)=>{
        this.url=event.target.result;
        localStorage.setItem("recent-image",event.target.result);
        console.log(event.target.result);
      }
    }
  }

  showNotification(from, align,color){

    switch(color){

      case 2:
        this.toastr.success('<span class="now-ui-icons ui-1_bell-53"></span> Type de badge bien ajouter.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-success alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 3:
        this.toastr.warning('<span class="now-ui-icons ui-1_bell-53"></span> Type de badge bien modifier.', '', {
          timeOut: 8000,
          closeButton: true,
          enableHtml: true,
          toastClass: "alert alert-warning alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;
      case 4:
        this.toastr.error('<span class="now-ui-icons ui-1_bell-53"></span> Type de badge bien supprimer.', '', {
          timeOut: 8000,
          enableHtml: true,
          closeButton: true,
          toastClass: "alert alert-danger alert-with-icon",
          positionClass: 'toast-' + from + '-' +  align
        });
        break;

      default:
        break;
    }
  }
//file transfaire




  // define a function to upload files
  onUploadFiles(files: File[]): void {
    if(files){
      this.imageIpud=files;
      console.log('image uload');
      var reader=new FileReader();
      reader.readAsDataURL(files[0]);
      this.selectFile=<File>files[0];

      reader.onload=(event:any)=>{
        this.url=event.target.result;
      //  localStorage.setItem("recent-image",event.target.result);
      }
    }
   /* const formData = new FormData();
    for (const file of files) { formData.append('files', file, file.name); }
    this.fileService.upload(formData).subscribe(
      event => {
        console.log(event);
        this.resportProgress(event);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );*/
  }

  // define a function to download files
  onDownloadFile(filename: string): void {
    this.fileService.download(filename).subscribe(
      event => {
        console.log(event);
        this.resportProgress(event);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch(httpEvent.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Uploading... ');
        break;
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Downloading... ');
        break;
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent);
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
          this.fileStatus.status = 'done';
          //console.log("myaw");
          for (const filename of httpEvent.body) {
            this.filenames.unshift(filename);
          }

        } else {
          saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!,
            {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
         // console.log("enregistre");

          // saveAs(new Blob([httpEvent.body!],
          //   { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}),
          //    httpEvent.headers.get('File-Name'));
        }
        this.fileStatus.status = 'done';

        break;
      default:
        console.log(httpEvent);
        break;

    }
  }

  private updateStatus(loaded: number, total: number, requestType: string): void {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }

  onEditProduct(p: any) {
   this.typeBadgeE=p;
   this.etat=2;
  this.url="./assets/badge/"+p.chart;
  this.imageIpud=p.chart;
  }

  onUpdateProduct(value: any) {

    const formData = new FormData();
    //rest api add type badge start
    console.log("Name picture "+this.imageIpud[0].name)
    console.log(value);
    if (this.imageIpud[0].name){
      value.chart=this.imageIpud[0].name;
    }else {
      value.chart=this.imageIpud;
    }
    console.log(value);
   this.typeBadgeService.updatTypeBadges(this.typeBadgeService.host+"/TYPE-BADGE-SERVICE/typeBadges",value)
      .subscribe(resp=>{
        this.showNotification('top', 'center',3);
        // image upload start
        for (const file of this.imageIpud) { formData.append('files', file, file.name); }
        this.fileService.upload(formData).subscribe(
          event => {
            console.log(event);
            console.log(this.fileStatus.percent);
            this.resportProgress(event);
          },
          (error: HttpErrorResponse) => {
            console.log(error);
          }
        );
        // image upload end
      },err => {
        console.log(err);
      })
    //end
  }
}

