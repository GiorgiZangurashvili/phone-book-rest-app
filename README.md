[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/Q_uUPVAm)
# Java Spring Bootcamp 02 homework
### Week 17, Assignment 2
#### Phone Book CRUD app

---
თქვენი ამოცანაა დაწეროთ კონტაქტების შესანახი აპლიკაცია.

კონტაქტების აპლიკაციაში უნდა შეიძლებოდეს როგორც პიროვნებების(ფიზიკური პირების) კონტაქტების შენახვა ასევე კომპანიების(იურიდიული პირების) კონტაქტების შენახვა 
**პიროვნების** დამატების შემთხვევაში უნდა შეიძლებოდეს დამატებით _სახელი_, _გვარი_, _დაბადების თარიღი_(მხოლოდ თვე და რიცხვის შეყვანაც შეიძლება), _კომენტარი_-ის შეყვანაც.  
**კომპანიის** დამატების შემთხვევაში კომპანიის _სახელი_ და _კომენტარი_. კომპანიაზე პირდაპირ არ შეიძლება კონტაქტის მიბმა. კომპანიას აქვს თავისი საკონტაქტო (_სახელი_ და _კომენტარი_), რომელზეც შეიძლება მიებას კონტაქტი. ეს საკონტაქტი შეიძლება იყოს ფილიალი ან კონკრეტული პირიც.  
რაც შეეხება უშუალოდ **კონტაქტს**, ის შედგება 2 ნაწილისგან ტიპი/კატეგორია (label) და უშუალოდ მნიშვნელობა. ზოგიერთი ტიპი(label) წინასწარ არის განსაზღვრული მაგალითად: _მობ.ნომერი_, _სამსახურის ნომერი_, _სახლის ნომერი_, _პირადი მეილი_, _სამსახურის მეილი_, _მისამართი_, _სხვა_. `სხვა`ს დამატების შემთხვევაში ასევე უნდა დაამატოს რეალური `label` რა იქნება.  

უნდა შეძლებოდე კონტაქტის დამატება, რედაქტირება წაშლა.  
უნდა შეიძლებოდეს პიროვნების დამერჯვა კომპანიაზე ამ შემთხვევაში უნდა შეიქმნას ახალი საკონტაქტო, რომლის სახელს გადმოყვება პიროვნების სახელის და გვარის კონტატენაცია. კომენტარი უცვლელად გამდოყვება თუ არის. ხოლო კონტაქტებიდან გადმოვა სამსახურის მეილი და ნომერი. თუ არ არსებობს სამსახურის მეილი ან ნომერი მაშინ შეცდომა დააბუნეთ.


მაგალითი: ( არ არის აუცილებელი თქვენი სტრუქტურა 100%-ით ასე გამოიყურებოდეს)
* ფიზიკური პირი: 
```json
{
    "name": "მიხელი",
    "surname": "ჭელიძე",
    "contacts": [
        {
            "label": "მობ.ნომერი",
            "value": "512346789"
        },
        {
            "label": "სახლის ნომერი",
            "value": "032212346789"
        },
        {
            "label": "სამსახურის მეილი",
            "value": "m.tchelidze@omedia.dev"
        },
        {
            "label": "მისამართი",
            "value": "150 - ების ბოლო გაჩერება"
        },
        {
            "label": "ბიძაშვილის ნომერი",
            "value": "ეს ალბათ მიხვდით რომ სხვა ველია"
        }
    ]
} 
```
* იურიდიული პირი:
``` json
{
    "name": "omedia",
    "comment": "software development",
    "contactors" [
        {
            "name": "მიხეილ",
            "comment": "developer",
            "contacts": [
                {
                    "label": "მობ.ნომერი",
                    "value": "512346789"
                },
                {
                    "label": "სამსახურის მეილი",
                    "value": "m.tchelidze@omedia.dev"
                },
                {
                    "label": "მისამართი",
                    "value": "შანხაი"
                },
                {
                    "label": "ბიძაშვილის ნომერი",
                    "value": "ეს ალბათ მიხვდით რომ სხვა ველია"
                }
            ]
        },
        {
            "name": "hot line",
            "contacts": [
                {
                    "label": "ცხელი ხაზი",
                    "value": "112"
               }]
        }
    ]
}
```
p.s.  შეიძლება დაგჭრდეთ
* @javax.persistence.Inheritance 
* @javax.persistence.MappedSuperclass 
* javax.persistence.InheritanceType
